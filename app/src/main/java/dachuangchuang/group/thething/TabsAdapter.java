package dachuangchuang.group.thething;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dachuangchuang.group.thething.database.Tab;

/**
 * Created by 边园 on 2016/2/4.
 */
public class TabsAdapter extends BaseAdapter {
    private Context context;
    private List<Tab> list;

    private int mSelection;
    private boolean mHasSelection;

    public TabsAdapter(Context context, List<Tab> list) {
        this.context = context;
        this.list = list;

        init();
    }

    private void init() {
        mSelection = -1;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.item_tabs_main, null);

        TextView textView = (TextView) convertView.findViewById(R.id.main_page_tabs_item_name);
        textView.setText(this.list.get(position).getTabName());

        if (mHasSelection) {
            if (mSelection == position) {
                textView.setBackgroundColor(Color.YELLOW);
            } else {
                textView.setBackgroundColor(Color.TRANSPARENT);
            }
        }

        return textView;
    }

    public int getSelection() {
        return mSelection;
    }

    public void setSelection(int position) {
        if (mHasSelection && mSelection == position)
            mHasSelection = false;
        else {
            mHasSelection = true;
            mSelection = position;
        }
    }

    public boolean isSelected() {
        return mHasSelection;
    }
}
