package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Charadas.R;

import java.util.ArrayList;



public class GroupAdapter extends BaseAdapter {
    public LayoutInflater layoutInflater;
    private ArrayList<int[]> groups;

    public GroupAdapter(LayoutInflater layoutInflater, ArrayList<int[]> groups) {
        this.layoutInflater = layoutInflater;
        this.groups = groups;
    }

    public void update(int groupNumber, int scoreToadd) {
        this.groups.set(groupNumber, new int[] {groupNumber, this.groups.get(groupNumber)[1] + scoreToadd});
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.groups.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = this.layoutInflater.inflate(R.layout.group_item_layout, null);

        ImageView groupIcon = convertView.findViewById(R.id.groupIcon);


        TextView groupTitle = convertView.findViewById(R.id.groupTitle);

        TextView groupScore = convertView.findViewById(R.id.groupScore);

        groupIcon.setImageResource(R.drawable.groups);

        groupScore.setText(String.valueOf(this.groups.get(position)[1]));


        groupTitle.setText("Grupo " + String.valueOf(position + 1));

        return convertView;

    }
}