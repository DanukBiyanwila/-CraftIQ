import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import CommentView from '../components/CommentView'
import CommentData from '../data/CommentData'
import CommentCreate from '../components/CommentCreate'
import SlideBar from '../components/SlideBar'
import SkillPostSingle from '../components/skillPost/SkillPostSingle'
function SkillPostDetails() {
  const { id } = useParams();
  const [skillPost, setSkillPost] = useState(null);
  const [loading, setLoading] = useState(true);
const [author, setAuthor] = useState(null);

  // Optionally: handle comments
  const [comments, setComments] = useState([]);

  useEffect(() => {
  axios.get(`http://localhost:8086/api/skillposts/${id}`)
  .then(response => {
    const post = response.data;
    const formattedPost = {
      id: post.id,
      img: post.imageBase64
        ? `data:image/jpeg;base64,${post.imageBase64}`
        : '',
      title: post.title,
      summary: post.summary,
      pargrhap_1: post.pargrhap1,
      pargrhap_2: post.pargrhap2,
      pargrhap_3: post.pargrhap3,
      pargrhap_4: post.pargrhap4,
      pargrhap_5: post.pargrhap5,
      category: post.category || "General",
      commentCount: post.commentCount || 0,
      user: post.user,
    };

    setSkillPost(formattedPost);

    // Fetch author data
    if (post.user && post.user.id) {
      axios.get(`http://localhost:8086/api/user/${post.user.id}`)
        .then(userResponse => {
          const userData = userResponse.data;
          const formattedAuthor = {
            fullName: userData.fullName,
            profileImage: userData.imageBase64
              ? `data:image/jpeg;base64,${userData.imageBase64}`
              : '',
          };
          setAuthor(formattedAuthor);
        })
        .catch(err => {
          console.error("Error fetching author:", err);
        });
    }

    setLoading(false);
  })
  .catch(error => {
    console.error("Error fetching skill post:", error);
    setLoading(false);
  });



  }, [id]);

  if (loading) return <div>Loading...</div>;
  if (!skillPost) return <div>Post not found</div>;

  return (
    <section className="blog_area single-post-area section-padding">
      <div className="container">
        <div className="row">
          <div className="col-lg-8 posts-list">
            <SkillPostSingle skillPost={skillPost}  author={author} />

            <div className="comments-area">
              <h4>{comments.length} Comments</h4>
              {CommentData.slice(0, 4).map(comment => (
                <CommentView key={comment.id} comment={comment} />
              ))}
            </div>

            <CommentCreate postId={id} />
          </div>

          <div className="col-lg-4">
            <SlideBar />
          </div>
        </div>
      </div>
    </section>
  );
}

export default SkillPostDetails;
