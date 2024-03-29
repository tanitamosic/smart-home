import React, { useEffect } from 'react';
import '../../assets/styles/business.css';
import { Row, Col, Button } from 'react-bootstrap';
import { useNavigate  } from "react-router-dom";    
import { getRole } from '../../services/utils/AuthService';

export default function AdminFirstPage(){

    const navigate = useNavigate ();

    const certificatesButtonPressed = (e) => {
        navigate('/admin/certificates');
    }

    const requestsButtonPressed = (e) => {
        navigate('/admin/requests');
    }

    const clientsButtonPressed = (e) => {
        navigate('/admin/clients');
    }

    const allObjectsButtonPressed = (e) => {
        navigate('/admin/all-objects');
    }

    const newObjectButtonPressed = (e) => {
        navigate('/admin/new-object');
    }

    const createAlarmButtonPressed = (e) => {
        navigate('/admin/create-alarm');
    }

    const logsButtonPressed = (e) => {
        navigate('/logs');
    }

    const reportsButtonPressed = (e) => {
        navigate('/reports');
    }

    const logoutButtonPressed = (e) => {
        navigate('/logout');
    }

    const userRole = getRole();

    useEffect(() => {
        if(userRole !== "admin"){
            navigate("/unavailable");
        }
    }, [navigate, userRole])

    return <>
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={certificatesButtonPressed}>
                        Certificates
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={requestsButtonPressed}>
                        Requests
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={clientsButtonPressed}>
                        Clients
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={allObjectsButtonPressed}>
                        All objects
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={newObjectButtonPressed}>
                        New object
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={createAlarmButtonPressed}>
                        Create alarm
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={logsButtonPressed}>
                        Logs
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={reportsButtonPressed}>
                        Reports
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
            <Row className='mt-2'>
                <Col sm={4}/>
                <Col sm={4} align='center'>
                    <Button className='formButton' onClick={logoutButtonPressed}>
                        Logout
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row> 
        </>
}