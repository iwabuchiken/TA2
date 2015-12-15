package ta2.items;

public class BM {

	long	dbId;

	String	created_at;
	String	modified_at;

	String	position;
	
	long		ta_id;
	String	ta_text;
	
	String	title;
	String	memo;
	
	public BM() {
		
	}

	public BM(Builder builder) {
		// TODO Auto-generated constructor stub
		dbId = builder.dbId;
		this.created_at = builder.created_at;
		this.modified_at = builder.modified_at;
		
		position		= builder.position;
		
		this.ta_id		= builder.ta_id;
		this.ta_text	= builder.ta_text;
		
		this.title		= builder.title;
		this.memo		= builder.memo;
		
	}//public BM(Builder builder)

	
	public String getTitle() {
		return title;
	}

	public String getMemo() {
		return memo;
	}

	public void setTa_id(long ta_id) {
		this.ta_id = ta_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public long getTa_id() {
		return ta_id;
	}

	public String getPosition() {
		return position;
	}

	public String getTa_text() {
		return ta_text;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public void setTa_id(int ta_id) {
		this.ta_id = ta_id;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setTa_text(String ta_text) {
		this.ta_text = ta_text;
	}


	public long getDbId() {
		return dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public static class Builder {

		private long	dbId;
		String	created_at;
		String	modified_at;

		private String	position;
		
		long		ta_id;
		String	ta_text;

		String	title;
		String	memo;

		public BM build() {
			return new BM(this);
		}

		public Builder setDbId(long dbId) {
			this.dbId = dbId; return this;
		}

		public Builder setCreated_at(String created_at) {
			this.created_at = created_at; return this;
		}

		public Builder setModified_at(String modified_at) {
			this.modified_at = modified_at; return this;
		}

		public Builder setPosition(String position) {
			this.position = position; return this;
		}

		public Builder setTa_id(long l) {
			this.ta_id = l; return this;
		}

		public Builder setTa_text(String ta_text) {
			this.ta_text = ta_text; return this;
		}

		
		public Builder setTitle(String title) {
			this.title = title; return this;
		}

		public Builder setMemo(String memo) {
			
			this.memo = memo; return this;
			
		}

	}//public static class Builder
	
}//public class BM
