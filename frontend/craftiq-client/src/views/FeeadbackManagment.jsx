import React, { useState } from 'react';

function FeeadbackManagment() {
  const [isEditing, setIsEditing] = useState(false);
  const [feedback, setFeedback] = useState(
    'Multiply sea night grass fourth day sea lesser rule open subdue female fill which them Blessed, give fill lesser bearing multiply sea night grass fourth day sea lesser'
  );

  const handleEditClick = () => {
    setIsEditing(true);
  };

  const handleSaveClick = () => {
    setIsEditing(false);
  };

  const handleChange = (e) => {
    setFeedback(e.target.value);
  };

  return (
    <div className="whole-wrap">
      <div className="container box_1170">
        <div className="section-top-border">
          <h3 className="mb-30">
            Second divided from form fish beast made every of seas all gathered us saying he our
          </h3>
          <div className="row">
            <div className="col-lg-12">
              {isEditing ? (
                <textarea
                  className="form-control"
                  rows="5"
                  value={feedback}
                  onChange={handleChange}
                  style={{ marginBottom: '15px' }}
                />
              ) : (
                <blockquote className="generic-blockquote">{feedback}</blockquote>
              )}

              {isEditing ? (
                <button className="btn btn-success mr-2" onClick={handleSaveClick}>
                  Save
                </button>
              ) : (
                <button className="btn btn-primary mr-2" onClick={handleEditClick}>
                  Edit
                </button>
              )}

              <button className="btn btn-danger">Delete</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default FeeadbackManagment;
