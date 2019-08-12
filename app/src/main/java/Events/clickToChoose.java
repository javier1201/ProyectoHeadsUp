package Events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Charadas.ChooseActivity;

public class clickToChoose implements View.OnClickListener {
    private int whosTurn;
    private Context context;

    public clickToChoose(Context context, int whosTurn) {
        this.context = context;
        this.whosTurn = whosTurn;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.context, ChooseActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("group", whosTurn);
        intent.putExtras(bundle);


    }
}
