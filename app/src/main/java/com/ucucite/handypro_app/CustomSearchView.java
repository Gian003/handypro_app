package com.ucucite.handypro_app;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CustomSearchView extends LinearLayout {

    EditText searchEditText;

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_search_view, this, true);
        searchEditText = findViewById(R.id.searchbar_text);
    }

    public String getText() {
        return searchEditText.getText().toString();
    }

    public void setText(String text) {
        searchEditText.setText(text);
    }

    public void setHint(String hint) {
        searchEditText.setHint(hint);
    }

    public void setQueryTextChanged(TextWatcher textWatcher) {
        searchEditText.addTextChangedListener(textWatcher);
    }
}
