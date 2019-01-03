{
  "id": "Tctest_${buildId}",
  "name": "${buildName}",
  "projectName": "${projectName}",
  "projectId": "${projectId}",
  "project": {
    "id": "${projectId}",
    "name": "${projectName}",
    "parentProjectId": "_Root"
  },
  "templates": {
    "count": 1,
    "buildType": [
      {
        "id": "Tctest_DistributedBuildConfigurationTemplate",
        "name": "Distributed Build Configuration Template",
        "templateFlag": true,
        "projectName": "Tctest",
        "projectId": "Tctest",
        "href": "/app/rest/buildTypes/id:Tctest_DistributedBuildConfigurationTemplate"
      }
    ]
  },
  "vcs-root-entries": {
    "count": 1,
    "vcs-root-entry": [
      {
        "id": "Tctest_HttpsGithubComLeukosTctestGitRefsHeadsMaster",
        "inherited": true,
        "vcs-root": {
          "id": "Tctest_HttpsGithubComLeukosTctestGitRefsHeadsMaster",
          "name": "https://github.com/leukos/tctest.git#refs/heads/master",
          "href": "/app/rest/vcs-roots/id:Tctest_HttpsGithubComLeukosTctestGitRefsHeadsMaster"
        },
        "checkout-rules": ""
      }
    ]
  },
  "settings": {
    "count": 1,
    "property": [
      {
        "name": "buildNumberCounter",
        "value": "4"
      }
    ]
  },
  "parameters": {
    "count": 0,
    "href": "/app/rest/buildTypes/id:Tctest_DistributedBuild3/parameters",
    "property": []
  },
  "steps": {
    "count": 1,
    "step": [
      {
        "id": "RUNNER_2",
        "name": "",
        "type": "Maven2",
        "properties": {
          "count": 9,
          "property": [
            {
              "name": "goals",
              "value": "test -Dtest=com.tctest.MainTest -Dare.mode.distributed=true"
            },
            {
              "name": "maven.path",
              "value": "%teamcity.tool.maven.DEFAULT%"
            },
            {
              "name": "pomLocation",
              "value": "pom.xml"
            },
            {
              "name": "teamcity.coverage.emma.include.source",
              "value": "true"
            },
            {
              "name": "teamcity.coverage.emma.instr.parameters",
              "value": "-ix -*Test*"
            },
            {
              "name": "teamcity.coverage.idea.includePatterns",
              "value": "*"
            },
            {
              "name": "teamcity.coverage.jacoco.patterns",
              "value": "+:*"
            },
            {
              "name": "teamcity.step.mode",
              "value": "default"
            },
            {
              "name": "userSettingsSelection",
              "value": "userSettingsSelection:default"
            }
          ]
        }
      }
    ]
  },
  "features": {
    "count": 0
  },
  "triggers": {
    "count": 0
  },
  "snapshot-dependencies": {
    "count": 0
  },
  "artifact-dependencies": {
    "count": 0
  },
  "agent-requirements": {
    "count": 0
  }
}