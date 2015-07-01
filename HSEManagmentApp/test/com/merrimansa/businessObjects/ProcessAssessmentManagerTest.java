/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.entities.ProcessAssessment;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Steve
 */
public class ProcessAssessmentManagerTest {
    
    public ProcessAssessmentManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAssessment method, of class ProcessAssessmentManager.
     */
    @Test
    public void testFindAssessment() {
        System.out.println("findAssessment");
        int AssessmentId = 0;
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        ProcessAssessment expResult = null;
        ProcessAssessment result = instance.findAssessment(AssessmentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAssessment method, of class ProcessAssessmentManager.
     */
    @Test
    public void testUpdateAssessment() {
        System.out.println("updateAssessment");
        ProcessAssessment assessment = null;
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        instance.updateAssessment(assessment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAssignedAssessments method, of class ProcessAssessmentManager.
     */
    @Test
    public void testGetAssignedAssessments() {
        System.out.println("getAssignedAssessments");
        UserVO currentUser = null;
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        List<ProcessAssessment> expResult = null;
        List<ProcessAssessment> result = instance.getAssignedAssessments(currentUser);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignAssessment method, of class ProcessAssessmentManager.
     */
    @Test
    public void testAssignAssessment() {
        System.out.println("assignAssessment");
        RequiredAssessmentVO reqAssess = null;
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        instance.assignAssessment(reqAssess);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRequiredAssessments method, of class ProcessAssessmentManager.
     */
    @Test
    public void testGetRequiredAssessments() {
        System.out.println("getRequiredAssessments");
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        List<RequiredAssessmentVO> expResult = null;
        List<RequiredAssessmentVO> result = instance.getRequiredAssessments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTeamMember method, of class ProcessAssessmentManager.
     */
    @Test
    public void testAddTeamMember() {
        System.out.println("addTeamMember");
        int UserID = 0;
        int AssessmentId = 0;
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        instance.addTeamMember(UserID, AssessmentId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubmittedAssessments method, of class ProcessAssessmentManager.
     */
    @Test
    public void testGetSubmittedAssessments() {
        System.out.println("getSubmittedAssessments");
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        List<ProcessAssessment> expResult = null;
        List<ProcessAssessment> result = instance.getSubmittedAssessments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApproved method, of class ProcessAssessmentManager.
     */
    @Test
    public void testSetApproved() {
        System.out.println("setApproved");
        int assessmentId = 0;
        ProcessAssessmentManager instance = new ProcessAssessmentManager();
        instance.setApproved(assessmentId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
