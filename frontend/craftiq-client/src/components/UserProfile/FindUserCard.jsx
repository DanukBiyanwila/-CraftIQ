import React from 'react'

function FindUserCard({ user }) {
  return (
    <div>
      <div className="section-top-border">
        <div className="row">
          <div className="col-md-12 d-flex flex-column align-items-center text-center">
            <div className="single-defination">
              <img
                src={user.img}
                alt={user.title}
                style={{ width: '300px', height: '300px', objectFit: 'cover', borderRadius: '50%' }}
              />
              <h3 className="mt-3">{user.title}</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default FindUserCard
