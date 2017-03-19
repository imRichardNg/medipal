package iss.nus.medipal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Category;
import iss.nus.medipal.R;
import iss.nus.medipal.activity.EditCategoryActivity;
import iss.nus.medipal.application.App;

/**
 * Created by Robbin on 17/3/17.
 */
public class CategoryListAdapter extends ArrayAdapter<Category> {
    private Context applicationContext;
    private List<Category> categoryList;


    public CategoryListAdapter(Context applicationContext) {
        super(applicationContext, R.layout.category_list_layout);
        this.applicationContext = applicationContext;
        categoryList = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final Category category = categoryList.get(position);

        LayoutInflater inflater =
                (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.category_list_layout, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.tv_category = (TextView) convertView.findViewById(R.id.tv_category);
        viewHolder.tv_categoryDescription = (TextView) convertView.findViewById(R.id.tv_categoryDescription);
        viewHolder.tv_code = (TextView) convertView.findViewById(R.id.tv_code);
        viewHolder.isReminder = (TextView) convertView.findViewById(R.id.tv_reminderID);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), EditCategoryActivity.class);
                intent.putExtra("Category", category);
                getContext().getApplicationContext().startActivity(intent);
            }
        });

        convertView.setTag(viewHolder);

        viewHolder.id = category.getCategoryID();
        viewHolder.tv_category.setText(category.getCategory());
        viewHolder.tv_categoryDescription.setText(category.getCategoryDescription());
        viewHolder.tv_code.setText(category.getCode());

        String description = category.getCategoryDescription();
        if (TextUtils.isEmpty(description)) {
            description = "-";
        }
        viewHolder.tv_categoryDescription.setText(description);

        return convertView;
    }

    public void refreshCategoryList() {
        categoryList.clear();
        categoryList.addAll(App.medipal.getCategoryList(getContext().getApplicationContext()));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    static class ViewHolder {
        int id;
        TextView tv_category;
        TextView tv_code;
        TextView tv_categoryDescription;
        TextView isReminder;
    }

}
