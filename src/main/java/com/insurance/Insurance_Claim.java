package com.insurance;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Insurance_Claim {

	public String name;
	public List<String> type;
	public double paidMonths;
	private String priority;
	public double age;

	
	int flag = 0;
	Boolean result;

	public boolean newInsurancePolicy() {
		if (flag == 0) {
			Insurance_NewPolicy newPolicy = new Insurance_NewPolicy();
			result = newPolicy.applyNewPolicy(name, type, paidMonths, age);

			flag = 1;
		}
		return result;
	}

	public String priorityCheck() {
		String prior = checkPolicy(type, paidMonths);

		if (prior.equals("HIGH") || prior.equals("MEDIUM")
				|| prior.equals("LOW")) {
			return prior;
		} else {
			priority = "NOT_Applicable";
			return "NOT_Applicable";
		}
	}

	private String checkPolicy(List<String> type, double paidMonths) {

		for (String typeOfPolicy : type) {
			if (typeOfPolicy.toUpperCase().equals("VEHICLE")) {
				if (paidMonths >= 54) {
					priority = "HIGH";
				} else if (paidMonths >= 42) {
					priority = "MEDIUM";
				} else {
					priority = "LOW";
				}
			} else if (typeOfPolicy.toUpperCase().equals("HEALTH")) {
				if (paidMonths >= 54) {
					priority = "HIGH";
				} else if (paidMonths >= 42) {
					priority = "MEDIUM";
				} else {
					priority = "LOW";
				}
			} else if (typeOfPolicy.toUpperCase().equals("LIFE")) {
				if (age != 0) {
					lifeInsurance(paidMonths);
				}
			} else if (typeOfPolicy.toUpperCase().equals("THEFT")) {
				if (paidMonths >= 30) {
					priority = "HIGH";
				} else if (paidMonths >= 18) {
					priority = "MEDIUM";
				} else {
					priority = "LOW";
				}
			} else if (typeOfPolicy.toUpperCase().equals("HOME")) {
				if (paidMonths >= 96) {
					priority = "HIGH";
				} else if (paidMonths >= 84) {
					priority = "MEDIUM";
				} else {
					priority = "LOW";
				}
			}

		}

		return priority;

	}

	private String lifeInsurance(double paidMonths) {
		if (paidMonths >= 60 && (age >= 40 || age <= 60)) {
			priority = "HIGH";
		} else if (paidMonths >= 36 && (age >= 45 || age <= 50)) {
			priority = "MEDIUM";
		} else {
			priority = "LOW";
		}

		return name;

	}

	public String getName() {
		return name;
	}

	public List<String> getType() {
		return type;
	}

	public double getPaidMonths() {
		return paidMonths;
	}

	public double getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public void setPaidMonths(double paidMonths) {
		this.paidMonths = paidMonths;
	}

	public void setAge(double age) {
		this.age = age;
	}

}
