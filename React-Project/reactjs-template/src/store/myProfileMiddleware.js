import { setAcceptLanguage } from '../agent/defaultAxios';
import { MY_PROFILE } from './actionConstants';

const myProfileMiddleWare = store => next => action => {
    if (action.type === MY_PROFILE && !action.error) {
        const locale = action.payload.data.locale;
        console.log("myProfileMiddleWare: locale = ", locale);
        setAcceptLanguage(locale);
    }
    next(action);
}

export default myProfileMiddleWare;