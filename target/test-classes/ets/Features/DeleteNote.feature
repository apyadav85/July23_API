Feature: Delete Notes

  Scenario: Delete a note
    Given user have access of application
    When user wants to delete a note "/notes"
      | noteid                   |
      | 64cc60ebd1562c00f724d0c3 |
    Then validate delete note status code 200
    And validate delete note success message "Note successfully deleted"
