package ta2.items;

public class AudioMemo {

	// id in database
	long db_Id;

	String created_at;
	
	String modified_at;

	String text;
	
	String file_name;
	
	String dir;
	
	String last_Modified;
	
	String length;
	
	public AudioMemo(Builder builder) {
		// TODO Auto-generated constructor stub
		
		this.db_Id	= builder.db_Id;

		this.created_at	= builder.created_at;
		
		this.modified_at	= builder.modified_at;
		
		this.text	= builder.text;
		
		this.dir	= builder.dir;
		
		this.last_Modified	= builder.last_Modified;

		this.length	= builder.length;
		
		this.file_name	= builder.file_name;
		
	}
	
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getLast_Modified() {
		return last_Modified;
	}



	public void setLast_Modified(String last_Modified) {
		this.last_Modified = last_Modified;
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
		
		String last_Modified;

		String length;

		String file_name;
		
		public AudioMemo build() {
			
			return new AudioMemo(this);
		}

		
		
		public Builder setFile_name(String file_name) {
			this.file_name = file_name; return this;
		}



		public Builder setLength(String length) {
			this.length = length; return this;
		}



		public Builder setLast_Modified(String last_Modified) {
			this.last_Modified = last_Modified; return this;
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
