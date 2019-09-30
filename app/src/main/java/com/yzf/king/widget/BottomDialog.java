package com.yzf.king.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.ChooseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzsjjas
 */
public class BottomDialog extends Dialog {
    public static final int MODE_BUTTON = 0;

    public static final int MODE_SINGLE = 1;

    private Context context;

    private int mode = MODE_BUTTON;

    private int checkIndex = -1;

    public BottomDialog(Context context) {
        super(context, R.style.BottomDialog);
        this.context = context;
    }

    public void setMode(int mode, int index) {
        this.mode = mode;
        checkIndex = index;
    }

    public interface OnAlertSelectId {
        void onClick(int whichButton);
    }

    public void showAlert(final String title, final ArrayList<ChooseItem> items, final OnAlertSelectId alertDo) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.alert_dialog_menu_layout, null);
        layout.setMinimumWidth(10000);
        final ListView list = (ListView) layout.findViewById(R.id.content_list);
        AlertAdapter adapter = new AlertAdapter(context, title, items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDo.onClick(position);
                dismiss();
                list.requestFocus();
            }
        });
        layout.findViewById(R.id.bottom_cancel).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (alertDo != null) {
                    alertDo.onClick(-1);
                }
                dismiss();
            }
        });
        // set a large value put it in bottom
        Window w = getWindow();
        WindowManager.LayoutParams lp = w != null ? w.getAttributes() : null;
        lp.x = 0;
        final int cMakeBottom = -1000;
        lp.y = cMakeBottom;
        lp.gravity = Gravity.BOTTOM;
        onWindowAttributesChanged(lp);
        setCanceledOnTouchOutside(true);
        setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (alertDo != null) {
                    alertDo.onClick(-1);
                }
            }
        });

        setContentView(layout);
        show();

    }

    class AlertAdapter extends BaseAdapter {

        private List<ChooseItem> items = new ArrayList<>();

        private boolean hasTitle = false;

        private Context context;

        public AlertAdapter(Context context, String title, ArrayList<ChooseItem> items) {
            this.context = context;
            this.items.addAll(items);
            if (title != null && !title.equals("")) {
                this.hasTitle = true;
                this.items.add(0, new ChooseItem(title, ""));
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean isEnabled(int position) {
            return !(position == 0 && hasTitle) && super.isEnabled(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChooseItem item = items.get(position);
            if (position == 0 && hasTitle) {
                convertView = View.inflate(context, R.layout.alert_dialog_menu_list_layout_title, null);
            } else if (mode == MODE_BUTTON) {
                convertView = View.inflate(context, R.layout.alert_dialog_menu_list_layout, null);
            } else if (mode == MODE_SINGLE) {
                convertView = View.inflate(context, R.layout.alert_dialog_menu_list_layout_single, null);
                CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkbox);
                cb.setChecked(checkIndex == position);
            }
            ((TextView) convertView.findViewById(R.id.popup_text)).setText(item.title);
            return convertView;
        }

    }
}
