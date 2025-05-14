import React from 'react'
import { Link } from 'react-router-dom';
function RecentPostCard({ skillpost }) {
    return (
        <div>
            <div className="media post_item mb-10">
                <img src={skillpost.img} alt="post" style={{ width: '80px', height: '80px' }} />
                <div className="media-body">
                     <Link className="d-inline-block" to={`/user/skill-post-details/${skillpost.id}`}>
                        <h3> {skillpost.title} </h3>
                   </Link>
                    {skillpost.date && skillpost.date.length > 0 && (
            <p>
              {skillpost.date[0].day} {skillpost.date[0].month} {skillpost.date[0].year}
            </p>
          )}
                </div>
            </div>
        </div>
    )
}

export default RecentPostCard