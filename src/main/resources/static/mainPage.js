function showStoredContent() {
        var storedSectionId = localStorage.getItem('lastSelectedSection');
        if (storedSectionId) {
            showContent(storedSectionId);
        } else {
            // Default to showing 'manage' section if nothing is stored
            showContent('manage');
        }
    }
    function showContent(sectionId) {
        // Hide all content sections
        var contentSections = document.querySelectorAll('#content > div');
            contentSections.forEach(function (section) {
                section.style.display = 'none';
        });

        // Show the selected content section
        var selectedSection = document.getElementById(sectionId);
        if (selectedSection) {
            selectedSection.style.display = 'block';
            //store the currently selected section
            localStorage.setItem('lastSelectedSection', sectionId);
        }
    }