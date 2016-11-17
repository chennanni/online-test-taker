package org.learn.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="test")
public class Test {
	@Id
	@GeneratedValue
	@Column(name="test_id")
	private int id;
	
	private int score;
	
	@Column(name="test_name")
	private String name;
	
	private Date startTime;
	private Date endTime;
	private Date allowedDuration;
	private Date usedDuration;
	
	@ManyToMany
	@JoinTable(name="test_question", 
			   joinColumns={@JoinColumn(name="test_id")},
			   inverseJoinColumns={@JoinColumn(name="question_id")})
	private List<Question> questions = new ArrayList<Question>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getAllowedDuration() {
		return allowedDuration;
	}
	public void setAllowedDuration(Date allowedDuration) {
		this.allowedDuration = allowedDuration;
	}
	public Date getUsedDuration() {
		return usedDuration;
	}
	public void setUsedDuration(Date usedDuration) {
		this.usedDuration = usedDuration;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
