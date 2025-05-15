import React from 'react'

function Footer() {
  return (
    <div>
         <footer>
        {/* Footer Start*/}
        <div className="footer-main footer-bg">
          <div className="footer-area footer-padding">
            <div className="container">
              <div className="row d-flex justify-content-between">
                <div className="col-xl-3 col-lg-3 col-md-5 col-sm-8">
                  <div className="single-footer-caption mb-50">
                    <div className="single-footer-caption mb-30">
                      {/* logo */}
                      <div className="footer-logo">
                        <a href="index.html"><img src="assets/img/logo/logo2_footer.png" alt="" /></a>
                      </div>
                      <div className="footer-tittle">
                        <div className="footer-pera">
                          <p className="info1">Craft IQ is your smart hub for mastering creative skills—learn, create, and elevate your craft with expert guidance.</p>
                          <p className="info2">No. 27, Creative Tower ,Galle Road, Colombo 03,Western Province, Sri Lanka</p>
                          <p className="info2">Phone: +94 (0) 112 365 789<br></br> Cell: +94 (0) 715 456 789</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col-xl-4 col-lg-4 col-md-5 col-sm-7">
                  <div className="single-footer-caption mb-50">
                    <div className="footer-tittle">
                      <h4>Popular post</h4>
                    </div>
                    {/* Popular post */}
                    <div className="whats-right-single mb-20">
                      <div className="whats-right-img">
                        <img src="assets/img/gallery/footer_post1.png" alt="" />
                      </div>
                      <div className="whats-right-cap">
                        <h4><a href="latest_news.html">Scarlett’s disappointment at latest accolade</a></h4>
                        <p>Jhon  |  2 hours ago</p> 
                      </div>
                    </div>
                    {/* Popular post */}
                    <div className="whats-right-single mb-20">
                      <div className="whats-right-img">
                        <img src="assets/img/gallery/footer_post2.png" alt="" />
                      </div>
                      <div className="whats-right-cap">
                        <h4><a href="latest_news.html">Scarlett’s disappointment at latest accolade</a></h4>
                        <p>Jhon  |  2 hours ago</p> 
                      </div>
                    </div>
                    {/* Popular post */}
                    <div className="whats-right-single mb-20">
                      <div className="whats-right-img">
                        <img src="assets/img/gallery/footer_post3.png" alt="" />
                      </div>
                      <div className="whats-right-cap">
                        <h4><a href="latest_news.html">Scarlett’s disappointment at latest accolade</a></h4>
                        <p>Jhon  |  2 hours ago</p> 
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col-xl-3 col-lg-3 col-md-5 col-sm-7">
                  <div className="single-footer-caption mb-50">
                    <div className="banner">
                      <img src="assets/img/gallery/body_card4.png" alt="" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          {/* footer-bottom aera */}
          <div className="footer-bottom-area footer-bg">
            <div className="container">
              <div className="footer-border">
                <div className="row d-flex align-items-center">
                  <div className="col-xl-12 ">
                    <div className="footer-copy-right text-center">
                      <p>{/* Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. */}
                        Copyright © All rights reserved by <a href="" target="_blank">Craft IQ</a>
                        {/* Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. */}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* Footer End*/}
      </footer>
    </div>
  )
}

export default Footer