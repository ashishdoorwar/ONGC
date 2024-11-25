package com.ongc.liferay.model;

public class IncidentBean {

		private int id;
		private String category;
		private String description;
		private String date;
		private String subject;
		private String posteddby;
		private String postedbyName;
		private String fname;
		private int index;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPostedbyName() {
			return postedbyName;
		}
		public void setPostedbyName(String postedbyName) {
			this.postedbyName = postedbyName;
		}
		public String getPosteddby() {
			return posteddby;
		}
		public void setPosteddby(String posteddby) {
			this.posteddby = posteddby;
		}

		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		
		@Override
		public String toString() {
			return "IncidentBean [id=" + id + ", category=" + category + ", description=" + description + ", date="
					+ date + ", subject=" + subject + ", posteddby=" + posteddby + ", postedbyName=" + postedbyName
					+ ", fname=" + fname + ", index=" + index + "]";
		}
		
}
