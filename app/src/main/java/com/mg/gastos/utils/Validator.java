package com.mg.gastos.utils;

import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Iterator;

public class Validator {

    public static boolean passRequired(TextView textView) {
        String text = textView.getText().toString();
        boolean passed = true;

        if (StringUtils.isBlank(text)) {
            passed = false;
            textView.setError("Este campo es requerido.");
        }

        return passed;
    }

    public static boolean passRequired(TextView[] textViews) {

        boolean res = true;

        Iterator<TextView> textViewIterator = Arrays.stream(textViews).iterator();

        while (textViewIterator.hasNext()) {
            TextView textView = textViewIterator.next();
            boolean passed = passRequired(textView);

            if (res)
                res = passed;
        }


        return res;
    }
}
