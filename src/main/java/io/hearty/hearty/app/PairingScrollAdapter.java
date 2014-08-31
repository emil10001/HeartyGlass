package io.hearty.hearty.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.List;

/**
 * Created by ejf3 on 5/18/14.
 */
public class PairingScrollAdapter extends CardScrollAdapter {
    private List<Card> mCards;

    public PairingScrollAdapter(List<Card> mCards, CardScrollView mCardScrollView, Context context) {
        this.mCards = mCards;
    }

    @Override
    public int getPosition(Object item) {
        return mCards.indexOf(item);
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public Object getItem(int position) {
        return mCards.get(position);
    }

    /**
     * Returns the amount of view types.
     */
    @Override
    public int getViewTypeCount() {
        return Card.getViewTypeCount();
    }

    /**
     * Returns the view type of this card so the system can figure out
     * if it can be recycled.
     */
    @Override
    public int getItemViewType(int position) {
        return mCards.get(position).getItemViewType();
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        return mCards.get(position).getView(convertView, parent);
    }

}
