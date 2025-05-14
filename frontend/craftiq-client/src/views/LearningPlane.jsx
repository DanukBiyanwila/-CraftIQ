import React, { useState } from 'react';
import Swal from 'sweetalert2';
import LearningPlaneData from '../data/LearningPlaneData';
import LearningPlaneCard from '../components/learningPlane/LearningPlaneCard';
import { useNavigate } from 'react-router-dom';
function LearningPlane() {
  const navigate = useNavigate();


  return (
    <div >
       <button className="btn btn-primary mt-40 ml-10" onClick={() => navigate('/user/plane-create')} >
         Add learning Plane
      </button>

     {LearningPlaneData.map(LearningPlane => (
                  <LearningPlaneCard key={LearningPlane.id} LearningPlane={LearningPlane} />
                ))}



    </div>
  );
}

export default LearningPlane;
