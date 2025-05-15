import React, { useEffect, useState } from 'react';
import AllSkillPost from '../components/skillPost/AllSkillPost';
import axios from 'axios';

function ViewSkillPost() {
  const [userSkillPosts, setUserSkillPosts] = useState([]);
  const currentUser = JSON.parse(localStorage.getItem('user'));

  useEffect(() => {
    axios.get('http://localhost:8086/api/skillposts/')
      .then(response => {
        const filtered = response.data
          .filter(post => post.user?.id === currentUser?.id)
          .map(post => ({
            id: post.id,
            img: post.imageBase64 ? `data:image/jpeg;base64,${post.imageBase64}` : '', // add fallback if needed
            title: post.title,
            summary: post.summary,
            pargrhap_1: post.pargrhap1,
            pargrhap_2: post.pargrhap2,
            pargrhap_3: post.pargrhap3,
            pargrhap_4: post.pargrhap4,
            pargrhap_5: post.pargrhap5,
            category: post.category || 'General',
            date: new Date(post.createdAt),
            user: post.user
          }));
        setUserSkillPosts(filtered);
      })
      .catch(error => {
        console.error('Error fetching skill posts:', error);
      });
  }, [currentUser?.id]);

  return (
    <div>
      <h3 className="mb-3 mt-30 text-center">Your All Posts</h3>
      {userSkillPosts.map(skillPost => (
        <AllSkillPost key={skillPost.id} skillPost={skillPost} />
      ))}
    </div>
  );
}

export default ViewSkillPost;
