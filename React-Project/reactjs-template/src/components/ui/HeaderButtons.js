import React from 'react';
import './HeaderButtons.css';

function HeaderButtons (props){
    return(
        <div className="header-buttons">
            {props.children}
        </div>
    )
}

export default HeaderButtons;