package com.example.actionscatchlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<MyData> items;
    int position;
    Context context;

    // LayoutInflater – класс, который из
    // layout-файла создает View-элемент.
    private LayoutInflater inflater;

    // Слушает все изменения галочки и меняет
    // состояние конкретного ItemData

//    private CompoundButton.OnCheckedChangeListener myCheckChangeList
//            = new CompoundButton.OnCheckedChangeListener() {
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            items.get((Integer) buttonView.getTag()).setCheckbox(isChecked);
//        }
//    };

    // Конструктор, в который передается контекст
    // для создания контролов из XML. И первоначальный список элементов.
    MyAdapter(Context context, List<MyData> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Добавляет элемент в конец списка.
    // notifyDataSetChanged сообщает об обновлении данных и переотрисовывает.
    // Вы можете как угодно менять items в других местах.
    // Но не забывайте вызывать notifyDataSetChanged чтобы все обновилось.
    void addItem(MyData item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    // Удаляет элемент списка.
    void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    // Обязательный метод абстрактного класса BaseAdapter.
    // Он возвращает колличество элементов списка.
    @Override
    public int getCount() {
        return items.size();
    }

    // Тоже обязательный метод.
    // Должен возвращать элемент списка на позиции - position
    @Override
    public MyData getItem(int position) {
        if (position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

//    public void onClick(View view) {
//
//        if (view.getId() == R.id.buttonDel) {
//            items.remove(view.getId());
//        }
//    }


    public Button.OnClickListener myDel = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.buttonDel) {
                items.remove(view.getId());
            }
        }
    };

    // И это тоже обязательный метод.
    // Возвращает идентификатор. Обычно это position.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Самый интересный обязательный метод.
    // Создает или возвращает переиспользуемый View, с новыми данными
    // для конкретной позиции. BaseAdapter – хитрый класс,
    // он не держит в памяти все View - это дорого и будет тормозить.
    // Поэтому он рисует только то что видно. Для этого есть convertView.
    // Если нет чего переиспользовать, то создается новый View.
    // А потом напоняет старую или новую View нужными данными.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        MyData itemData = items.get(position);


        TextView title = view.findViewById(R.id.textViewHeader);
        TextView subtitle = view.findViewById(R.id.textViewText);
        final Button button = view.findViewById(R.id.buttonDel);

        title.setText(itemData.getHeader());
        subtitle.setText(itemData.getText());
        button.setTag(String.valueOf(position));
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int todel = Integer.parseInt((String) button.getTag());
                items.remove(todel);
                notifyDataSetChanged();
              //  Toast.makeText(context, "Привет, кнопка! ", Toast.LENGTH_SHORT).show();
            }
        });
        //view.setClickable(true);
      // view.setFocusable(true);
       // view.setBackgroundResource(android.R.drawable.menuitem_background);
        button.setFocusable(false);
        return view;
    }
}