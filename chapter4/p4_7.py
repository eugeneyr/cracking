"""
Build Order:

You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the first
project is dependent on the second project). All of a project's dependencies must be built before the project is.
Find the build order that will allow the projects to be built. If there is no valid build order, return an error.

EXAMPLE:

Input:
    projects:
        a, b, c, d, e, f
    dependencies:
        (d, a), (b, f), (d, b), (a, f), (c, d)
Output:
    f, e, a, b, d, c
"""

def findBuildOrder(projects, dependencies):
    """
    Finds the build order by performing topological sorting of the DAG that represents projects and their dependencies.

    :param projects: The list of projects.
    :param dependencies: The list of dependencies (each dependency is a two-element tuple or list).
    :return: The list of projects in a correct build order.
    """
    if projects is None or dependencies is None:
        return None

    remaining_deps = set(dependencies)
    removed_deps = set()
    remaining_projects = set(projects)
    build_order = []



