import React from 'react'
import post1 from "../assets/clients/img/post/post_1.png"
import post2 from "../assets/clients/img/post/post_2.png"
import post3 from "../assets/clients/img/post/post_3.png"
import post4 from "../assets/clients/img/post/post_4.png"
import SkillPostData from '../data/SkillPostData'
import RecentPostCard from './skillPost/RecentPostCard'

function SlideBar() {
  return (
    <div>
       <div className="blog_right_sidebar">
              
                <aside className="single_sidebar_widget post_category_widget">
                  <h4 className="widget_title">Category</h4>
                  <ul className="list cat-list">
                    <li>
                      <a href="#" className="d-flex">
                        <p>Resaurant food</p>
                        <p>(37)</p>
                      </a>
                    </li>
                    <li>
                      <a href="#" className="d-flex">
                        <p>Travel news</p>
                        <p>(10)</p>
                      </a>
                    </li>
                    <li>
                      <a href="#" className="d-flex">
                        <p>Modern technology</p>
                        <p>(03)</p>
                      </a>
                    </li>
                    <li>
                      <a href="#" className="d-flex">
                        <p>Product</p>
                        <p>(11)</p>
                      </a>
                    </li>
                    <li>
                      <a href="#" className="d-flex">
                        <p>Inspiration</p>
                        <p>(21)</p>
                      </a>
                    </li>
                    <li>
                      <a href="#" className="d-flex">
                        <p>Health Care</p>
                        <p>(21)</p>
                      </a>
                    </li>
                  </ul>
                </aside>
                <aside className="single_sidebar_widget popular_post_widget">
                  <h3 className="widget_title">Recent Post</h3>
                 {SkillPostData.slice(0, 6).map(skillpost => (
                  <RecentPostCard key={skillpost.id} skillpost={skillpost} />
                ))}
                
                </aside>
  
          
              </div>
    </div>
  )
}

export default SlideBar