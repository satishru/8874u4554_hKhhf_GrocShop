package app.groceryapp.ui.base;import android.view.View;import androidx.recyclerview.widget.RecyclerView;public abstract class BaseViewHolder extends RecyclerView.ViewHolder {    protected View itemView;    public BaseViewHolder(View itemView) {        super(itemView);        this.itemView = itemView;    }    public abstract void onBind(int position);}