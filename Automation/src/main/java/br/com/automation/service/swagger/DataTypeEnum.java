package br.com.automation.service.swagger;

public enum DataTypeEnum {

	STRING("string") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	INTEGER("integer") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	DOUBLE("double") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	LONG("long") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	BOOLEAN("boolean") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	DATETIME("date-time") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	FILE("file") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	OBJECT("object") {

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return null;
		}
	};


	public abstract String getValue();
	
	private String value;

	private DataTypeEnum(String value) {
		this.value = value;
	}
	
}
