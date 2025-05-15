import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2';
function UserDetailsEdit() {
    localStorage.getItem("user")

    const [userData, setUserData] = useState({
        fullName: '',
        email: '',
        tpNum: '',
        address: '',
        category: '',
        bio: ''
    });

    const navigate = useNavigate();

    const storedUser = localStorage.getItem("user");
    const userId = storedUser ? JSON.parse(storedUser)?.id : null;

    console.log("User Id : ", userId);



    useEffect(() => {
        if (!userId) {
            console.warn("No user ID found in localStorage.");
            return;
        }

        axios.get(`http://localhost:8086/api/user/${userId}`)
            .then(response => {
                setUserData(response.data);
            })
            .catch(error => {
                console.error("Failed to fetch user:", error);
            });
    }, [userId]);


    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserData(prev => ({ ...prev, [name]: value }));
    };

 const handleSave = () => {
    axios.put(`http://localhost:8086/api/user/${userId}`, userData)
        .then(() => {
            Swal.fire({
                icon: 'success',
                title: 'Success!',
                text: 'User details updated successfully!',
                timer: 2000,
                showConfirmButton: false
            }).then(() => {
                navigate('/user/user-profile');
            });
        })
        .catch(error => {
            console.error("Failed to update user:", error);
            Swal.fire({
                icon: 'error',
                title: 'Update Failed',
                text: 'Something went wrong while updating the user.',
            });
        });
};
    return (
        <div>
            {/* Full Name */}
            <div className="row">
                <div className="col-sm-3"><h6 className="mb-0">Full Name</h6></div>
                <div className="col-sm-9 text-secondary">
                    <input
                        type="text"
                        className="form-control border-0 p-0"
                        name="fullName"
                        value={userData.fullName}
                        onChange={handleChange}
                        style={{ outline: "none", backgroundColor: "transparent" }}
                    />
                </div>
            </div>
            <hr />

            {/* Email */}
            <div className="row">
                <div className="col-sm-3"><h6 className="mb-0">Email</h6></div>
                <div className="col-sm-9 text-secondary">
                    <input
                        type="email"
                        className="form-control border-0 p-0"
                        name="email"
                        value={userData.email}
                        readOnly
                        style={{ outline: "none", backgroundColor: "transparent" }}
                    />

                </div>
            </div>
            <hr />

            {/* Phone */}
            <div className="row">
                <div className="col-sm-3"><h6 className="mb-0">Phone</h6></div>
                <div className="col-sm-9 text-secondary">
                    <input
                        type="tel"
                        className="form-control border-0 p-0"
                        name="tpNum"
                        value={userData.tpNum}
                        onChange={handleChange}
                        style={{ outline: "none", backgroundColor: "transparent" }}
                    />
                </div>
            </div>
            <hr />

            {/* Category */}
            <div className="row">
                <div className="col-sm-3"><h6 className="mb-0">Category</h6></div>
                <div className="col-sm-9 text-secondary">
                    <select
                        className="form-control border-0 p-0"
                        name="category"
                        value={userData.category}
                        onChange={handleChange}
                        style={{ outline: "none", backgroundColor: "transparent", color: "#6c757d" }}
                    >
                        <option value="">Select category</option>
                        <option value="Full Stack Developer">Full Stack Developer</option>
                        <option value="Photography">Photography</option>
                        <option value="BackEnd Developer">BackEnd Developer</option>
                        <option value="Top Contributor">Top Contributor</option>
                    </select>
                </div>
            </div>
            <hr />

            {/* Address */}
            <div className="row">
                <div className="col-sm-3"><h6 className="mb-0">Address</h6></div>
                <div className="col-sm-9 text-secondary">
                    <input
                        type="text"
                        className="form-control border-0 p-0"
                        name="address"
                        value={userData.address}
                        onChange={handleChange}
                        style={{ outline: "none", backgroundColor: "transparent" }}
                    />
                </div>
            </div>
            <hr />

            {/* Bio */}
            <div className="row">
                <div className="col-sm-3"><h6 className="mb-0">Bio</h6></div>
                <div className="col-sm-9 text-secondary">
                    <textarea
                        className="form-control border-0 p-0"
                        name="bio"
                        rows={3}
                        value={userData.bio}
                        onChange={handleChange}
                        style={{ outline: "none", backgroundColor: "transparent" }}
                    />
                </div>
            </div>
            <hr />

            {/* Save Button */}
            <div className="row">
                <div className="col-sm-12">
                    <button className="btn btn-info" onClick={handleSave}>Save</button>
                </div>
            </div>
        </div>
    );
}

export default UserDetailsEdit;
