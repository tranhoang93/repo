import React from 'react';
import { Link } from 'react-router-dom';

function NotLoggedInView(props) {
    console.log("[NotLoggedInView] Props: ", props);
    return (
        <div>
            <p>NotLoggedInView</p>
            <Link to="/login">Login</Link>
        </div>
    );
}

export default NotLoggedInView;