package com.example.agmookuapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class JobsViewHolder(private val jobsList :ArrayList<JobsClass>, var context: Context) : RecyclerView.Adapter<JobsViewHolder.JobViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder.JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_menu_layout,parent,false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobsViewHolder.JobViewHolder, position: Int) {
        val currentItem = jobsList[position]

        Picasso.get().load(currentItem.jobImageUrl).into(holder.jobListImage)// here maybe need to edit


        holder.jobListName.text = currentItem.jobName
        holder.jobListStatus.text = currentItem.workingStatus
        holder.jobListTime.text = currentItem.workingTime

        holder.itemView.setOnClickListener{
            val intent = Intent(context,JobsDetails::class.java)
            intent.putExtra("jobID",currentItem.jobID)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return jobsList.size
    }

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val jobListImage : ImageView = view.findViewById(R.id.jobsList_image)
        val jobListName : TextView = view.findViewById(R.id.jobsList_name)
        val jobListStatus : TextView = view.findViewById(R.id.jobsList_status)
        val jobListTime : TextView = view.findViewById(R.id.jobsList_time)

    }

}