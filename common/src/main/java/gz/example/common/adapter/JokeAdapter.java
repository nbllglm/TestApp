package gz.example.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gz.example.common.R;
import gz.example.common.CommonApplication;
import gz.example.common.entity.Joke;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ContentViewHolder> {
    private List<Joke> jokes;
    private Context mcontexts;

    public JokeAdapter(List<Joke> jokes, Context mcontexts) {
        this.jokes = jokes;
        this.mcontexts = mcontexts;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentViewHolder contentViewHolder = new ContentViewHolder((View) LayoutInflater.from(mcontexts).inflate(R.layout.item_joke, parent, false));
        return contentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        //holder.tvTittle.setText(jokes.get(position).getHashId());
        holder.tvContent.setText(jokes.get(position).getContent());
        holder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CommonApplication.context, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvContent;
        // private final TextView tvTittle;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            // tvTittle = itemView.findViewById(R.id.tv_joke_tittle);
            tvContent = itemView.findViewById(R.id.tv_joke_content);
        }

    }

}
