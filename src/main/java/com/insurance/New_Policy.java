package com.insurance;

public class New_Policy {

	public String policyType;
	public String policyId;
	public String name;
	public int age;
	public String type;
	public String nominee;
	public int noOfMonths;
	public String status;
	public long amount;
	public String priority;

	public New_Policy(String policyId, String name, int age, String type,
			String nominee, int noOfMonths) {
		this.policyId = policyId;
		this.name = name;
		this.age = age;
		this.type = type;
		this.nominee = nominee;
		this.noOfMonths = noOfMonths;
	}

	public New_Policy() {
	}

	public boolean insertPolicy() {
		Insurance_Check policy = new Insurance_Check();
		return policy.newPolicy(policyId, name, age, type, nominee, noOfMonths,
				status, priority);

	}

	public int claimPolicy() {
		Insurance_Check policy = new Insurance_Check();

		return policy.claimPolicy(policyId);

	}

	public String renewalPolicy() {
		Insurance_Check policy = new Insurance_Check();

		return policy.renewalPolicy(policyId);

	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
