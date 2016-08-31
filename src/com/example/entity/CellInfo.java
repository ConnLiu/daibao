package com.example.entity;

import cn.bmob.v3.BmobObject;

public class CellInfo extends BmobObject{
		private String head_path;
	    private String cellname;
	    private String grade;   //等级
	    private Integer publishnum;  //发布数
	    private Integer people;  //人气
	    private Integer add;   //新增商品数
	    private String hostId;  //创建者id

		public String getHead_path() {
			return head_path;
		}

		public void setHead_path(String head_path) {
			this.head_path = head_path;
		}
		
	    public String getName() {
	        return this.cellname;
	    }

	    public void setName(String name) {
	        this.cellname = name;
	    }

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public Integer getPublish() {
			return publishnum;
		}

		public void setPublish(Integer publishnum) {
			this.publishnum = publishnum;
		}

		public Integer getPeople() {
			return people;
		}

		public void setPeople(Integer people) {
			this.people = people;
		}

		public Integer getAdd() {
			return add;
		}

		public void setAdd(Integer add) {
			this.add = add;
		}

		public String getHostId() {
			return hostId;
		}

		public void setHostId(String hostId) {
			this.hostId = hostId;
		}

	}
