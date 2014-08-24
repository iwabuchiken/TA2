package ta2.items;

public class Memo {

	// id in database
	long db_Id;

	String created_at;
	
	String modified_at;

	String uploaded_at;
	
	String text;
	
	String twted_at;
	
	long twt_Id;
	
	String twt_created_at;

	public Memo(Builder builder) {
		// TODO Auto-generated constructor stub
		
		this.db_Id	= builder.db_Id;

		this.created_at	= builder.created_at;
		
		this.modified_at	= builder.modified_at;
		
		this.text	= builder.text;

		this.uploaded_at	= builder.uploaded_at;
		
		this.twted_at	= builder.twted_at;
		
		this.twt_Id	= builder.twt_Id;
		
		this.twt_created_at	= builder.twt_created_at;

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

	public String getUploaded_at() {
		return uploaded_at;
	}

	public String getTwted_at() {
		return twted_at;
	}

	public long getTwt_Id() {
		return twt_Id;
	}

	public String getTwt_created_at() {
		return twt_created_at;
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

	public void setUploaded_at(String uploaded_at) {
		this.uploaded_at = uploaded_at;
	}

	public void setTwted_at(String twted_at) {
		this.twted_at = twted_at;
	}

	public void setTwt_Id(long twt_Id) {
		this.twt_Id = twt_Id;
	}

	public void setTwt_created_at(String twt_created_at) {
		this.twt_created_at = twt_created_at;
	}
	
	public static class Builder {
		
		// id in database
		long db_Id;

		String created_at;
		
		String modified_at;

		String uploaded_at;
		
		String text;
		
		String twted_at;
		
		long twt_Id;
		
		String twt_created_at;

		public Memo build() {
			
			return new Memo(this);
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

		public Builder setUploaded_at(String uploaded_at) {
			this.uploaded_at = uploaded_at; return this;
		}

		public Builder setTwted_at(String twted_at) {
			this.twted_at = twted_at; return this;
		}

		public Builder setTwt_Id(long twt_Id) {
			this.twt_Id = twt_Id; return this;
		}

		public Builder setTwt_created_at(String twt_created_at) {
			this.twt_created_at = twt_created_at; return this;
		}

		
		
	}
	
}//public class Memo
