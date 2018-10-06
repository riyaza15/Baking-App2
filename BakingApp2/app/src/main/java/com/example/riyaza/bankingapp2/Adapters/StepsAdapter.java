package com.example.riyaza.bankingapp2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.riyaza.bankingapp2.R;
import com.example.riyaza.bankingapp2.model.Steps;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    final private ListItemClickListner mOnclickListner;
    ArrayList<Steps> steps;


    public StepsAdapter(ArrayList<Steps> steps,ListItemClickListner mOnclickListner) {
        this.steps = steps;
        this.mOnclickListner=  mOnclickListner;

    }



    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.steps_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        StepsAdapter.StepsViewHolder viewHolder = new StepsAdapter.StepsViewHolder(view);

        return viewHolder;
    }

    public interface ListItemClickListner{
        void onListItemClick(Steps steps);
    }



    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtShotDesc;
        TextView txtStepId;



        public StepsViewHolder(View view) {
            super(view);
            txtShotDesc =(TextView)view.findViewById(R.id.step_short_desc);
           // txtDesc=(TextView)view.findViewById(R.id.step_desc);
            txtStepId=(TextView) view.findViewById(R.id.stepId);
            itemView.setOnClickListener(this);

        }

        void bind( int listIndex) {
            Steps currentSteps= steps.get(listIndex)  ;
            String shortDesc= currentSteps.getStepShortDescription();
           // String description=currentSteps.getStepDescription();
            String id= currentSteps.getStepId();

            txtShotDesc.setText(shortDesc);
           // txtDesc.setText(description);
            txtStepId.setText(id);



        }

        @Override
        public void onClick(View v) {
            int clickPosition= getAdapterPosition();
            mOnclickListner.onListItemClick(steps.get(clickPosition));
        }
    }

    public void setStepsData(List<Steps> stepsData) {
        steps = (ArrayList<Steps>) steps;
        notifyDataSetChanged();
    }
}
