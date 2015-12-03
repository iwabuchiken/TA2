package ta2.items;

public class AudioMemo {

	// id in database
	long db_Id;

	String created_at;
	
	String modified_at;

	String text;
	
	String dir;
	
	public AudioMemo(Builder builder) {
		// TODO Auto-generated constructor stub
		
		this.db_Id	= builder.db_Id;

		this.created_at	= builder.created_at;
		
		this.modified_at	= builder.modified_at;
		
		this.text	= builder.text;
		
		this.dir	= builder.dir;

	}

	
	public String getDir() {
		return dir;
	}


	public void setDir(String dir) {
		this.dir = dir;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getDb_Id() {
		return db_Id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getModified_at() {
		return modified_at;
	}


	public void setDb_Id(long db_Id) {
		this.db_Id = db_Id;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public static class Builder {
		
		// id in database
		long db_Id;

		String created_at;
		
		String modified_at;

		String text;

		String dir;
		
		public AudioMemo build() {
			
			return new AudioMemo(this);
		}

		
		
		public Builder setDir(String dir) {
			
			this.dir = dir; return this;
			
		}



		public Builder setText(String text) {
			this.text = text; return this;
		}

		public Builder setDb_Id(long db_Id) {
			this.db_Id = db_Id; return this;
		}

		public Builder setCreated_at(String created_at) {
			this.created_at = created_at; return this;
		}

		public Builder setModified_at(String modified_at) {
			this.modified_at = modified_at; return this;
		}

	}//public static class Builder
	
}//public class Memo