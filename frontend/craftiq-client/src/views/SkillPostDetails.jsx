import React from 'react'
import CommentView from '../components/CommentView'
import CommentData from '../data/commentData'
import CommentCreate from '../components/CommentCreate'
import SlideBar from '../components/SlideBar'
import SkillPostSingle from '../components/skillPost/SkillPostSingle'
import { useParams } from 'react-router-dom';

function SkillPostDetails() {
  const { id } = useParams();
  return (
    <div>
          <section className="blog_area single-post-area section-padding">
        <div className="container">
          <div className="row">
            <div className="col-lg-8 posts-list">
              <SkillPostSingle/>
              
            
              <div className="comments-area">
                <h4>05 Comments</h4>
                {CommentData.slice(0, 4).map(comment => (
                  <CommentView key={comment.id} comment={comment} />
                ))}
                
                
               
              </div>
              <CommentCreate/>
            </div>
            <div className="col-lg-4">
              <SlideBar/>
            </div>
          </div>
        </div>
      </section>
    </div>
  )
}

export default SkillPostDetails