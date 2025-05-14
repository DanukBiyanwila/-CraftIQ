import React from "react";
import { useParams } from "react-router-dom";
import EditLearningPlancomponent from "../components/learningPlane/EditLearningPlan"; // adjust path if needed
import LearningPlaneData from "../data/LearningPlaneData"; // your data file

function EditLearningPlane() {
  const { id } = useParams();
  const numericId = parseInt(id, 10);

  const selectedLearningPlane = LearningPlaneData.find(
    (plane) => plane.id === numericId
  );

  return (
    <div className="container mt-5">
      {selectedLearningPlane ? (
        <EditLearningPlancomponent LearningPlane={selectedLearningPlane} />
      ) : (
        <p>Learning Plan not found.</p>
      )}
    </div>
  );
}

export default EditLearningPlane;
