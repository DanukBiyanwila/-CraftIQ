import React from 'react'

import team2 from "../../assets/clients/img/gallery/team2.png"
import team1 from "../../assets/clients/img/gallery/team1.png"
import team3 from "../../assets/clients/img/gallery/team3.png"
function FollowerCard( {user} ) {
  return (


  
            <div className="col-xl-4 col-lg-4 col-md-6 col-sm-">
              <div className="single-team mb-30">
                <div className="team-img">
                  <img src={user.img} alt="" style={{ width: '370px', height: '463px' }} />
                </div>
                <div className="team-caption">
                  <h3><a href="#">{user.name} </a></h3>
                  <span> {user.category} </span>
                </div>
              </div>
            </div>
 
       
  )
}

export default FollowerCard