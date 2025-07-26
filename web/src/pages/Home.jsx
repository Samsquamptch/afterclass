import { useNavigate } from 'react-router-dom';

function Home() {
    const navigate = useNavigate();

  return(
    <>
        <div className="pl-96 pr-96 pt-10 text-center font-archivo">
            <div className="">
                <h1 className="text-4xl">The Ultimate Post-Study Meetup Planner for Uni Students</h1>
                <p className='text-xl p-5'>Want to meet up with university friends, but don’t know which days you have classes?
                    Save time and effort by using our site to organise your friends. Create a new group, share the link with anyone, and get ready to have a few beers!</p>
            </div>
            <div className="buttons">
                <button 
                className="p-2 bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid text-white text-xl"
                onClick={() => navigate('/groups')}>Click Here to Start</button>
            </div>
            <div className="size-7/12 mx-auto mt-10 mb-10">
                <img src="src\assets\table.png"></img>
            </div>
            <div className="mb-4">
                <p>Please note that new groups only last for seven days and will be deleted after this time period.
                    Hopefully you’ve all worked out which days are the best for hanging out at the local uni bar after a lecture by then.</p>   
            </div>
        </div>
    </>
  )
};

export default Home;
