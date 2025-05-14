import React from 'react'
import FollowerCard from '../components/UserProfile/FollowerCard'
import UserData from '../data/UserData'

function Follower() {
  return (
    <div>

        <div className="team-area section-padding30">
                <div className="container">
                  <div className="row">
                    <div className="cl-xl-7 col-lg-8 col-md-10">
                      {/* Section Tittle */}
                      <div className="section-tittles mb-70">
                        <span>Your Folowers </span>
                        <h2>Folowers</h2>
                      </div> 
                    </div>
                  </div>
                   <div className="row">
    
                  {UserData.map(user => (
                                            <FollowerCard key={user.id} user={user} />
                                        ))}
                                        </div>
                </div>
              </div>
    </div>
  )
}

export default Follower