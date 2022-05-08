package com.edating.dto;

import java.util.List;

import org.hibernate.validator.constraints.URL;

public class InterestDto {
	
    private Long id;
    
	private String likes;
	private String dislikes;
	
	
	private List<String> hobbies;
	
	@URL
	private String profileURL;
	private String about;
	
    private UserDto user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getDislikes() {
		return dislikes;
	}

	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((about == null) ? 0 : about.hashCode());
	    result = prime * result + ((dislikes == null) ? 0 : dislikes.hashCode());
	    result = prime * result + ((hobbies == null) ? 0 : hobbies.hashCode());
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    result = prime * result + ((likes == null) ? 0 : likes.hashCode());
	    result = prime * result + ((profileURL == null) ? 0 : profileURL.hashCode());
	    result = prime * result + ((user == null) ? 0 : user.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    InterestDto other = (InterestDto) obj;
	    if (about == null) {
		if (other.about != null)
		    return false;
	    } else if (!about.equals(other.about))
		return false;
	    if (dislikes == null) {
		if (other.dislikes != null)
		    return false;
	    } else if (!dislikes.equals(other.dislikes))
		return false;
	    if (hobbies == null) {
		if (other.hobbies != null)
		    return false;
	    } else if (!hobbies.equals(other.hobbies))
		return false;
	    if (id == null) {
		if (other.id != null)
		    return false;
	    } else if (!id.equals(other.id))
		return false;
	    if (likes == null) {
		if (other.likes != null)
		    return false;
	    } else if (!likes.equals(other.likes))
		return false;
	    if (profileURL == null) {
		if (other.profileURL != null)
		    return false;
	    } else if (!profileURL.equals(other.profileURL))
		return false;
	    if (user == null) {
		if (other.user != null)
		    return false;
	    } else if (!user.equals(other.user))
		return false;
	    return true;
	}
	
	
	
}
