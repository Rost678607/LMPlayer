package com.softwareforpeople.lmplayer.ui.screens.List

class TracksAdapter(
    private val tracks: List<Track>,
    private val onFavoriteClick: (Track) -> Unit) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.track_title)
        val favoriteButton: Button = itemView.findViewById(R.id.favorite_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.title.text = track.title
        holder.favoriteButton.text = if (track.isFavorite) "Убрать из избранного" else "Добавить в избранное"

        holder.favoriteButton.setOnClickListener {
            onFavoriteClick(track)
        }
    }
    override fun getItemCount() = tracks.size
}