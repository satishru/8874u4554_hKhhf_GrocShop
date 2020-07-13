package app.groceryapp.ui.custom;import android.content.Context;import android.content.res.TypedArray;import android.graphics.Typeface;import android.util.AttributeSet;import androidx.appcompat.widget.AppCompatButton;import androidx.core.content.res.ResourcesCompat;import app.groceryapp.R;import app.groceryapp.enums.FontTypeEnum;public class GroceryButton extends AppCompatButton {    public GroceryButton(Context context) {        super(context);    }    public GroceryButton(Context context, AttributeSet attrs) {        super(context, attrs);        setFontFamily(context, attrs);    }    public GroceryButton(Context context, AttributeSet attrs, int defStyleAttr) {        super(context, attrs, defStyleAttr);        setFontFamily(context, attrs);    }    public void setFontFamily(Context context, AttributeSet attrs) {        int setFontFamily;        TypedArray typedArray = context.getTheme().obtainStyledAttributes(            attrs, R.styleable.GroceryButton, 0, 0);        try {            setFontFamily = typedArray.getInteger(R.styleable.GroceryButton_custom_btn_font, 0);            setTypeface(getTypeFace(setFontFamily));        } finally {            typedArray.recycle();        }    }    public Typeface getTypeFace(int type) {        Typeface typeface = null;        try {            typeface = ResourcesCompat.getFont(getContext(), FontTypeEnum.getValue(type).getFontId());        } catch (Exception e) {            e.printStackTrace();        }        return typeface;    }}