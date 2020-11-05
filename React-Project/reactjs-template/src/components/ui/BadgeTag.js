import React from 'react';
import './BadgeTag.css';

function BadgeTag (props){
    const style = {};
    if(props.backgroundColor){
        style['backgroundColor'] = props.backgroundColor;
    } else {
        style['backgroundColor'] = '#ff4d4f';
    }
    if(props.textColor){
        style['color'] = props.textColor;
    } else {
        style['color'] = '#fff';
    }
    return(
        <span className="badge-tag" style={style}>
            {props.children}
        </span>
    )
}

export default BadgeTag;