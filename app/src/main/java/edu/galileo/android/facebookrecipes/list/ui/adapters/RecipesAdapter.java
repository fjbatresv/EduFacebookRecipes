package edu.galileo.android.facebookrecipes.list.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;

/**
 * Created by javie on 22/06/2016.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    private List<Recipe> recipeList;
    private ImageLoader imageLoader;
    private OnItemClickListener listener;

    public RecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener listener) {
        this.recipeList = recipeList;
        this.imageLoader = imageLoader;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_recipes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = recipeList.get(position);
        imageLoader.load(holder.imgRecipe, currentRecipe.getImageUrl());
        holder.txtRecipeName.setText(currentRecipe.getTitle());
        holder.imgFav.setTag(currentRecipe.getFavorite());
        if(currentRecipe.getFavorite()){
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_off);
        }
        holder.setOnItemClickLlistener(currentRecipe, listener);
    }

    public void setRecipes(List<Recipe> newRecipes){
        for (Recipe newRecipe : newRecipes){
            if(!recipeList.contains(newRecipe)){
                recipeList.add(newRecipe);
            }
        }
        notifyDataSetChanged();
    }

    public void removeRecipe(Recipe recipe){
        recipeList.remove(recipe);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imgRecipe)
        ImageView imgRecipe;
        @Bind(R.id.txtRecipeName)
        TextView txtRecipeName;
        @Bind(R.id.imgFav)
        ImageButton imgFav;
        @Bind(R.id.imgDelete)
        ImageButton imgDelete;
        @Bind(R.id.fbShare)
        ShareButton fbShare;
        @Bind(R.id.fbSend)
        SendButton fbSend;
        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
        public void setOnItemClickLlistener(final Recipe recipe, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(recipe);
                }
            });
            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFavoriteClick(recipe);
                }
            });
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(recipe);
                }
            });
            //Si no se le da contenido a los botones estoso se muestran deshabilitados
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(recipe.getSourceUrl())).build();
            fbShare.setShareContent(content);
            fbSend.setShareContent(content);
        }
    }
}
