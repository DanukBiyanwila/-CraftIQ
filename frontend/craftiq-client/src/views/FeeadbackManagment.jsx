import React, { useState, useEffect } from 'react';
import axios from 'axios';


function FeeadbackManagment() {
  // Filter comments where userId === 1


  const [editingId, setEditingId] = useState(null);
  const [editedFeedback, setEditedFeedback] = useState({});
    // Get logged-in user from localStorage
  const loggedInUser = JSON.parse(localStorage.getItem('user'));
  const loggedInUserId = loggedInUser?.id;


  const handleEditClick = (id, currentText) => {
    setEditingId(id);
    setEditedFeedback((prev) => ({ ...prev, [id]: currentText }));
  };

  const handleSaveClick = (id) => {
    // Save logic here if you want to persist to a backend
    setEditingId(null);
  };

  const handleChange = (e, id) => {
    setEditedFeedback((prev) => ({ ...prev, [id]: e.target.value }));
  };

    const [userComments, setUserComments] = useState([]);
  const [loading, setLoading] = useState(true);


  useEffect(() => {
    if (!loggedInUserId) {
      setLoading(false);
      return;
    }

    // Fetch all feedbacks
    axios.get('http://localhost:8086/api/feedback/')
      .then(async (res) => {
        const allFeedbacks = res.data;

        // Filter feedbacks for the logged in user
        const filteredFeedbacks = allFeedbacks.filter(fb => fb.userId === loggedInUserId);

        // Enrich each feedback with user image and skill post image
        const enrichedComments = await Promise.all(filteredFeedbacks.map(async (feedback) => {
          try {
            // Fetch user info for profile image
            const userRes = await axios.get(`http://localhost:8086/api/user/${feedback.userId}`);
            const user = userRes.data;
            const userImg = user.imageBase64 ? `data:image/jpeg;base64,${user.imageBase64}` : '';

            // Fetch skill post info for post image
            const postRes = await axios.get(`http://localhost:8086/api/skillposts/${feedback.skillPostId}`);
            const post = postRes.data;
            const postImg = post.imageBase64 ? `data:image/jpeg;base64,${post.imageBase64}` : '';

            return {
              id: feedback.id,
              comment: feedback.comment,
              author: user.fullName || feedback.author,
              userImg,
              postImg,
              postTitle: post.title || 'Untitled Post',
              date: feedback.createdAt ? new Date(feedback.createdAt).toLocaleDateString() : '',
            };
          } catch (err) {
            console.error('Error fetching data for feedback:', err);
            return null;
          }
        }));

        // Remove any nulls from failed fetches
        setUserComments(enrichedComments.filter(c => c !== null));
        setLoading(false);
      })
      .catch(err => {
        console.error('Error fetching feedbacks:', err);
        setLoading(false);
      });
  }, [loggedInUserId]);

  if (loading) return <p>Loading feedback...</p>;

  if (!loggedInUserId) return <p>Please login to see your feedback.</p>;


  return (
    <div className="whole-wrap">
      <div className="container box_1170">
        <div className="section-top-border">
          <h3 className="mb-30">User Feedback</h3>



         <div className="row">
            {userComments.map(comment => (
              <div className="col-lg-12 mb-5" key={comment.id}>
                <div className="d-flex align-items-center mb-4">
                  {/* User profile image */}
                  <img
                    src={comment.postImg}
                    alt={comment.author}
                    style={{
                      width: '60px',
                      height: '60px',
                      borderRadius: '50%',
                      marginRight: '15px',
                    }}
                  />
                  <div>
                    <strong>{comment.author}</strong><br />
                    <small>{comment.date}</small>
                  </div>
                </div>    
                <h5>{comment.postTitle}</h5>

                {editingId === comment.id ? (
                  <textarea
                    className="form-control"
                    rows="5"
                    value={editedFeedback[comment.id]}
                    onChange={(e) => handleChange(e, comment.id)}
                    style={{ marginBottom: '15px' }}
                  />
                ) : (
                  <blockquote className="generic-blockquote">
                    {editedFeedback[comment.id] || comment.comment}
                  </blockquote>
                )}

                {editingId === comment.id ? (
                  <button
                    className="btn btn-success mr-2"
                    onClick={() => handleSaveClick(comment.id)}
                  >
                    Save
                  </button>
                ) : (
                  <button
                    className="btn btn-primary mr-2"
                    onClick={() => handleEditClick(comment.id, comment.pargrhap)}
                  >
                    Edit
                  </button>
                )}

                <button className="btn btn-danger">Delete</button>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default FeeadbackManagment;
