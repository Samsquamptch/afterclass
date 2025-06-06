import { useNavigate } from 'react-router-dom';

function Home() {
    const navigate = useNavigate();

  return(
    <>
        <div className="title">
            <h1>The Ultimate Post-Study Meetup Planner for Uni Students</h1>
            <p>Want to meet up with university friends, but don’t know which days you have classes?
                Save time and effort by using our site to organise your friends. Create a new group, share the link with anyone, and get ready to have a few beers!</p>
        </div>
        <div className="buttons">
            <button onClick={() => navigate('/groups')} className="btn">Click Here to Start</button>
        </div>
        <div className="image">
            <image></image>
        </div>
        <div className="disclaimer">
            <p>Please note that new groups only last for seven days and will be deleted after this time period.
                Hopefully you’ve all worked out which days are the best for hanging out at the local uni bar after a lecture by then.</p>   
        </div>
    </>

  )
};

export default Home;
