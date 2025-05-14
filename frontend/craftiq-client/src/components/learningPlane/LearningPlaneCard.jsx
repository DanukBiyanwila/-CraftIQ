import React from "react";
import { useNavigate } from "react-router-dom";

function LearningPlaneCard({ LearningPlane }) {
  const navigate = useNavigate();

  const handleEdit = () => {
    navigate(`/user/edit-learning-plane/${LearningPlane.id}`);
  };

  return (
    <div>
      <div className="container mb-50 mt-50">
        <div className="w-95 w-md-75 w-lg-60 w-xl-55 mx-auto mb-6 text-center">
          <h2 className="display-18 display-md-16 display-lg-14 mb-10">
            {LearningPlane.title}
            <span
              className="text-primary ml-60 cursor-pointer"
              onClick={handleEdit}
            >
              Edit
            </span>
             <span
              className="text-primary ml-60 cursor-pointer"
              onClick={handleEdit}
            >
              Delete
            </span>
          </h2>
        </div>

        <div className="row">
          <div className="col-md-12">
            <div className="schedule-table">
              <table className="table bg-white">
                <tbody>
                  {LearningPlane.weeks.map((weekObj, index) => (
                    <tr key={index}>
                      <td className="day">{weekObj.week}</td>
                      {weekObj.milestones.map((milestone, i) => (
                        <td className="active" key={i}>
                          <h4>{milestone.title}</h4>
                          <p>{milestone.date}</p>
                          <div className="hover">
                            <h4>{milestone.title}</h4>
                            <p>{milestone.date}</p>
                            <span>{milestone.completed ? "Completed" : "Incomplete"}</span>
                          </div>
                        </td>
                      ))}
                      {weekObj.milestones.length < 3 &&
                        [...Array(3 - weekObj.milestones.length)].map((_, i) => (
                          <td key={`empty-${i}`} />
                        ))}
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LearningPlaneCard;
